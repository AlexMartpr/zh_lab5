public class MessageCache {
    private final String url;
    private final long time;

    public MessageCache(String url, Long time) {
        this.url = url;
        this.time = time;
    }

    public String getUrl() {
        return this.url;
    }

    public long getTime() {
        return this.time;
    }
}
