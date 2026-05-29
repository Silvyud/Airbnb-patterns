package Test;

import Interpreter.*;
import Utilities.Lodging;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterExpressionTest {

    @Test
    void evaluateSimpleExpressions() {
        Lodging lodging = new Lodging("L1", "Cartagena", 3, 2, 120.0, false);

        FilterExpression cartagenaFilter = new LocationFilter("Cartagena");
        FilterExpression roomFilter = new RoomFilter(3, new EqualEval());
        FilterExpression bathroomFilter = new BathroomFilter(2, new EqualEval());
        FilterExpression maxCostFilter = new CostFilter(150.0, new LessEqualEval());

        assertTrue(cartagenaFilter.evaluate(lodging));
        assertTrue(roomFilter.evaluate(lodging));
        assertTrue(bathroomFilter.evaluate(lodging));
        assertTrue(maxCostFilter.evaluate(lodging));

        FilterExpression bogotaFilter = new LocationFilter("Bogota");
        FilterExpression expensiveFilter = new CostFilter(100.0, new LessEqualEval());

        assertFalse(bogotaFilter.evaluate(lodging));
        assertFalse(expensiveFilter.evaluate(lodging));
    }

    @Test
    void evaluateComplexExpressions() {
        Lodging lodging = new Lodging("L1", "Cartagena", 3, 2, 120.0, false);

        FilterExpression cartagenaFilter = new LocationFilter("Cartagena");
        FilterExpression atLeastThreeRooms = new RoomFilter(3, new GreaterEqualEval());
        FilterExpression maxCost150 = new CostFilter(150.0, new LessEqualEval());
        FilterExpression atLeastTwoBathrooms = new BathroomFilter(2, new GreaterEqualEval());

        FilterExpression comfortableCartagenaLodging = new AndOperator(
                new AndOperator(cartagenaFilter, atLeastThreeRooms),
                new AndOperator(maxCost150, atLeastTwoBathrooms)
        );

        assertTrue(comfortableCartagenaLodging.evaluate(lodging));

        FilterExpression bogotaFilter = new LocationFilter("Bogota");
        FilterExpression cartagenaOrBogota = new OrOperator(cartagenaFilter, bogotaFilter);

        assertTrue(cartagenaOrBogota.evaluate(lodging));

        FilterExpression notBogota = new NotOperator(bogotaFilter);

        assertTrue(notBogota.evaluate(lodging));

        FilterExpression impossibleFilter = new AndOperator(
                cartagenaFilter,
                new CostFilter(80.0, new LessEqualEval())
        );

        assertFalse(impossibleFilter.evaluate(lodging));
    }

    @Test
    void evaluateAsFilterForVariousLodgings() {
        List<Lodging> lodgings = List.of(
                new Lodging("L1", "Cartagena", 3, 2, 120.0, false),
                new Lodging("L2", "Cartagena", 1, 1, 60.0, false),
                new Lodging("L3", "Bogota", 4, 3, 200.0, false),
                new Lodging("L4", "Cartagena", 4, 2, 180.0, true),
                new Lodging("L5", "Medellin", 2, 2, 90.0, false)
        );

        FilterExpression cartagenaFilter = new LocationFilter("Cartagena");
        FilterExpression atLeastTwoRooms = new RoomFilter(2, new GreaterEqualEval());
        FilterExpression atLeastTwoBathrooms = new BathroomFilter(2, new GreaterEqualEval());
        FilterExpression maxCost150 = new CostFilter(150.0, new LessEqualEval());

        FilterExpression lodgingFilter = new AndOperator(
                new AndOperator(cartagenaFilter, atLeastTwoRooms),
                new AndOperator(atLeastTwoBathrooms, maxCost150)
        );

        List<Lodging> filteredLodgings = lodgings.stream()
                .filter(lodgingFilter::evaluate)
                .toList();

        assertEquals(1, filteredLodgings.size());
        assertEquals("L1", filteredLodgings.getFirst().getId());
    }

    @Test
    void evaluateAlternativeComplexFilterForVariousLodgings() {
        List<Lodging> lodgings = List.of(
                new Lodging("L1", "Cartagena", 3, 2, 120.0, false),
                new Lodging("L2", "Cartagena", 1, 1, 60.0, false),
                new Lodging("L3", "Bogota", 4, 3, 200.0, false),
                new Lodging("L4", "Cartagena", 4, 2, 180.0, true),
                new Lodging("L5", "Medellin", 2, 2, 90.0, false)
        );

        FilterExpression cheapLodging = new CostFilter(100.0, new LessEqualEval());
        FilterExpression largeLodging = new AndOperator(
                new RoomFilter(4, new GreaterEqualEval()),
                new BathroomFilter(2, new GreaterEqualEval())
        );

        FilterExpression cheapOrLarge = new OrOperator(cheapLodging, largeLodging);

        List<Lodging> filteredLodgings = lodgings.stream()
                .filter(cheapOrLarge::evaluate)
                .toList();

        assertEquals(4, filteredLodgings.size());
        assertTrue(filteredLodgings.stream().anyMatch(lodging -> lodging.getId().equals("L2")));
        assertTrue(filteredLodgings.stream().anyMatch(lodging -> lodging.getId().equals("L3")));
        assertTrue(filteredLodgings.stream().anyMatch(lodging -> lodging.getId().equals("L4")));
        assertTrue(filteredLodgings.stream().anyMatch(lodging -> lodging.getId().equals("L5")));
    }
}