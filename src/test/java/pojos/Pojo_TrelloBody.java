package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pojo_TrelloBody {


    private String id;

    private String name;

    private String desc;

    private Object descData;

    private boolean closed;

    private String idOrganization;

    private Object idEnterprise;

    private boolean pinned;

    private String url;

    private String shortUrl;

    private Pojo_Prefs prefs;

    private Pojo_LabelNames labelNames;

    private Pojo_Limits limits;
}
