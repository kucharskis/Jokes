package History;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Joke {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "category",
            "icon_url",
            "id",
            "url",
            "value"
    })

    @JsonProperty("category")
    private String[] category;
    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private String value;
    @JsonProperty("icon_url")
    private String icon_url;
    @JsonProperty("url")
    private String url;



    public String getId() {
        return id;
    }
    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "category='" + category + '\'' +
                ", id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
