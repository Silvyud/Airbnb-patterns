import Proxy.*;

public class ProxyCommand implements SuperCommand {

    private Proxy proxy;

    public ProxyCommand(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public String execute() {
        return proxy.checkLocation();
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setUserAttemptingAccess(String userAttemptingAccess) {
        proxy.setUserAttemptingAccess(userAttemptingAccess);
    }

    public void setAccommodation(Accommodation accommodationData, String owner) {
        proxy.setRealAccommodation(accommodationData);
        proxy.setOwner(owner);
    }
}
