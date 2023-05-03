package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pojo_Prefs {


    private String permissionLevel;

    private boolean hideVotes;

    private String voting;

    private String comments;

    private String invitations;

    private boolean selfJoin;

    private boolean cardCovers;

    private boolean isTemplate;

    private String cardAging;

    private boolean calendarFeedEnabled;

    private String background;

    private Object backgroundImage;

    private Object backgroundImageScaled;

    private boolean backgroundTile;

    private String backgroundBrightness;

    private String backgroundColor;

    private String backgroundBottomColor;

    private String backgroundTopColor;

    private boolean canBePublic;

    private boolean canBeEnterprise;

    private boolean canBeOrg;

    private boolean canBePrivate;

    private boolean canInvite;
}
