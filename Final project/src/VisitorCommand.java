import Visitor.Visitor;
import Visitor.Element;

public class VisitorCommand implements SuperCommand {

    private Visitor visitor;
    private Element element;

    public VisitorCommand(Visitor visitor, Element element) {
        this.visitor = visitor;
        this.element = element;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String execute() {
        return element.accept(visitor);
    }
}
