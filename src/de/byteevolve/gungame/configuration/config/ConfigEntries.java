package de.byteevolve.gungame.configuration.config;

import de.byteevolve.gungame.configuration.ConfigEntry;

public enum ConfigEntries implements ConfigEntry {

    //DEFAULT
    PREFIX(ConfigSections.MESSAGES, "prefix", "&8[&aGun&2Game&8]", "&8[&aGun&2Game&8]", "Text vor jeder Nachricht."),
    NOPERM(ConfigSections.MESSAGES, "noperm", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "Nachricht wenn ein Spieler nicht die benötigten Rechte hat."),
    MUSTAPLAYER(ConfigSections.MESSAGES, "mustaplayer", "&7Du musst ein &aSpieler &7sein!", "&7Du musst ein &aSpieler &7sein!", "Nachricht an Entities, welche keine Spieler sind."),
    PLAYERNOTONLINE(ConfigSections.MESSAGES, "playernotonline", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "Nachricht wenn ein Spieler nicht online ist."),

    //ARENA COMMAND
    PLAYERCREATESARENA(ConfigSections.MESSAGES, "playercreatesarena", "&cDu erstellst schon eine Arena", "&cDu erstellst schon eine Arena", "Nachricht wenn ein Spieler eine Arena erstellen will, aber schon eine erstellt"),
    ARENAEXISTS(ConfigSections.MESSAGES, "arenaexists", "&7Die Arena:&a %ARENANAME% &7gibt es schon.", "&7Die Arena:&a %ARENANAME% &7gibt es schon.", "Nachricht wenn ein Spieler eine Arena erstellen will, welche schon vorhanden ist. \n%ARENANAME% = Platzhalter für den Arenanamen"),
    PLAYERCREATEARENA(ConfigSections.MESSAGES, "playercreatearena", "&7Du erstellst nun die Arena:&a %ARENANAME%&7. (&2/arena edit&7)", "&7Du erstellst nun die Arena:&a %ARENANAME%&7. (&2/arena edit&7)", "Nachricht wenn ein Spieler eine Arena erstellt. \n%ARENANAME% = Platzhalter für den Arenaname"),
    ARENANOTEXISTS(ConfigSections.MESSAGES, "arenanotexists",  "&7Die Arena:&a %ARENANAME% &7gibt es nicht.", "&7Die Arena:&a %ARENANAME% &7gibt es nicht.", "Nachricht wenn es eine Arena nicht gibt. \n%ARENANAME% = Platzhalter für den Arenanamen"),
    PLAYERNOTCREATESARENA(ConfigSections.MESSAGES, "playernotcreatesarena", "&7Du erstellst gerade keine Arena. (&2/arena create Name&7)", "&7Du erstellst gerade keine Arena. (&2/arena create Name&7)", "Nachricht wenn ein Spieler eine Arena mit /arena edit bearbeiten will, aber keine erstellt."),
    ARENAHELP(ConfigSections.MESSAGES, "arenahelp", "&7Nutze &c/arena &7<&ccreate&7,&cedit&7> &7<&cName&7> &7<&cDisplayname&7>", "&7Nutze &c/arena &7<&ccreate&7,&cedit&7> &7<&cName&7> &7<&cDisplayname&7>", "Hilfe Nachricht zum erstellen einer Arena"),
    NOARENAEXISTS(ConfigSections.MESSAGES, "noarenaexists","&cEs wurde noch keine Arena erstellt.", "&cEs wurde noch keine Arena erstellt.", "Nachricht an einen Spieler, wenn keine Arena gefunden wurde."),

    //BUILD COMMAND
    BUILDON(ConfigSections.MESSAGES, "buildon", "&7Du bist nun im &aBuild-Modus&7.","&7Du bist nun im &aBuild-Modus&7.", "Nachricht an einen Spieler, welcher in den Build Modus geht."),
    BUILDOFF(ConfigSections.MESSAGES, "buildoff", "&7Du bist nun &cnicht &7mehr im &aBuild-Modus&7.","&7Du bist nun &cnicht &7mehr im &aBuild-Modus&7.", "Nachricht an einen Spieler, welcher aus dem Build Modus geht."),

    //STATS COMMAND
    STATS(ConfigSections.MESSAGES, "stats", "&m&l&7-------- &aStats&7 -&a %PLAYER% &m&l&7-------- \n &7Kills:&a %KILLS% \n &7Tode:&a %DEAHTS% \n &7Rekord:&a %HIGHSCORE% \n &7Punkte:&a %POINTS% \n &7KD:&a %KD% \n &7Rang:&a #%RANK% \n &7&m&l-------------------------------------", "&m&l&7-------- &aStats&7 -&a %PLAYER% &m&l&7-------- \n &7Kills:&a %KILLS% \n &7Tode:&a %DEAHTS% \n &7Rekord:&a %HIGHSCORE% \n &7Punkte:&a %POINTS% \n &7KD:&a %KD% \n &7Rang:&a #%RANK% \n &7&m&l-------------------------------------", "Nachrichten welche bei dem Command /stats kommen. \n %PLAYER% = Spielername \n %KILLS% = Kills von dem Spieler \n %DEAHTS% = Tode von dem Spieler \n %POINTS% = Punkte von dem Spieler \n %HIGHSCORE% = Rekord von dem Spieler \n %KD% = KD von dem Spieler \n %RANK% = Rang von dem Spieler"),

    //TEMAS COMMAND
    NOTEAMALLOWED(ConfigSections.MESSAGES, "noteamallowed", "&7Bei dieser &aMap &7kannst du diesen Command &cnicht &7benutzen.", "&7Bei dieser &aMap &7kannst du diesen Command &cnicht &7benutzen.", "Nachricht an einen Spieler, welcher ein Team eröffnen will, bei einer Map, welche keine Teams erlaubt."),
    PLAYERINTEAM(ConfigSections.MESSAGES, "playerinteam", "&cDer Spieler ist schon in einem Team", "&cDer Spieler ist schon in einem Team", "Nachricht wenn ein Spieler versucht jemanden in ein Team einzuladen, welcher schon in einem ist."),
    PLAYERTEAMINVITE(ConfigSections.MESSAGES, "playerteaminvite", "&7Du hast&a %TARGET% &7zu einem Team eingeladen.", "&7Du hast&a %TARGET% &7zu einem Team eingeladen.", "Nachricht an den Spieler, der einen anderen in ein Team eingeladen hat. \n %TARGET% = Eingeladener Spielername"),
    PLAYERCANTINVITETEAMMEMBER(ConfigSections.MESSAGES,"playercantinviteteammember", "&cEs kann nur der Team-Inhaber andere einladen.", "&cEs kann nur der Team-Inhaber andere einladen.", "Nachricht an einen Spieler, welcher einen anderen Spieler zu einem Team einladen will, wo er nicht der Inhaber von ist."),
    TEAMCREATE(ConfigSections.MESSAGES, "teamcreate" , "&7Du hast ein &aTeam &7erstellt.", "&7Du hast ein &aTeam &7erstellt.", "Nachricht an einen Spieler, wenn er ein Team erstellt."),
    TEAMJOIN(ConfigSections.MESSAGES, "teamjoin", "&a%PLAYER% &7ist dem Team &2beigetreten&7.", "&a%PLAYER% &7ist dem Team &2beigetreten&7.", "Nachricht an alle Spieler im Team, wenn ein neuer das Team betritt. \n%PLAYER% = Spielername"),
    TEAMSIZE(ConfigSections.SETTINGS, "teamsize", 2, 2, "Team Größe ohne Inhaber"),
    TEAMFULL(ConfigSections.MESSAGES, "teamisfull", "&cDas Team ist schon voll.", "&cDas Team ist schon voll.", "Nachricht an einen Spieler, welcher ein Team betreten will, welches voll ist."),
    TEAMJOINPLAYER(ConfigSections.MESSAGES, "teamjoinplayer", "&7Du bist dem Team von&a %OWNER% &2beigetreten&7.", "&7Du bist dem Team von&a %OWNER% &2beigetreten&7.", "Nachricht an den Spieler, welcher ein Team betritt. \n %OWNER% = Inhabername des Teams"),
    PLAYERHASNOTEAMINVITE(ConfigSections.MESSAGES,"playerhasnoinvite", "&cDu wurdest zu keinem Team eingeladen.", "&cDu wurdest zu keinem Team eingeladen.", "Nachricht an einen Spieler, welcher in kein Team eingeladen wurde."),
    PLAYERISINTEAM(ConfigSections.MESSAGES, "playerisinteam", "&cDu bist schon in einem Team.", "&cDu bist schon in einem Team.", "Nachricht an einen Spieler, welcher in ein Team will, obwohl er schon in einem ist."),
    TARGETTEAMINVITE(ConfigSections.MESSAGES, "targetteaminvite", "&7Du wurdest von&a %PLAYER% &7zu einem &aTeam &7eingeladen.", "&7Du wurdest von&a %PLAYER% &7zu einem &aTeam &7eingeladen.",  "Nachricht an den eingeladenen Spieler \n %PLAYER% = Inhaber des Teams"),
    TEAMINVITEHELP(ConfigSections.MESSAGES, "teaminvitehelp", "&cBitte nutze /team invite <Player>", "&cBitte nutze /team invite <Player>", "Hilfe Nachricht zum einladen von Spielern"),
    TEAMDENYOWNER(ConfigSections.MESSAGES, "teamdenyowner", "&a%PLAYER% &7hat deine Anfrage &cabgelehnt&7.", "&a%PLAYER% &7hat deine Anfrage &cabgelehnt&7.", "Nachricht an den Inhaber eines Teams, wenn ein Spieler die Einladung ablehnt \n %PLAYER% = Eingeladeter Spieler"),
    TEAMDENYPLAYER(ConfigSections.MESSAGES, "teamdenyplayer", "&7Du hast die Anfrage von&a %OWNER% &cabgelehnt&7.", "&7Du hast die Anfrage von&a %OWNER% &cabgelehnt&7.", "Nachricht an den Spieler, welche eine Einladung zu einem Team abgelehnt hat. \n %OWNER% = Inhaber des Teams"),
    TEAMINFOOWNER(ConfigSections.MESSAGES, "teaminfoowner", "&cOwner&7:&a %OWNER%", "&cOwner&7:&a %OWNER%", "Inhaber Zeile bei /team info \n %OWNER% = Inhaber des Teams"),
    TEAMINFOMEMBER(ConfigSections.MESSAGES, "teaminfomember", "&7Member&7:&a %MEMBER%", "&7Member&7:&a %MEMBER%", "Mitglieder Zeile bei /team info \n %MEMBER% = Mitglied des Teams"),
    PLAYERNOTEAM(ConfigSections.MESSAGES, "playernoteam", "&cDu bist in keinem Team!", "&cDu bist in keinem Team!", "Nachricht an einen Spieler, welcher in keinem Team ist."),
    TEAMNEWOWNER(ConfigSections.MESSAGES, "teamnewowner", "&7Du bist nun der &cOwner &7des &aTeams&7.", "&7Du bist nun der &cOwner &7des &aTeams&7.", "Nachricht an den neuen Inhaber des Teams"),
    TEAMDELETE(ConfigSections.MESSAGES, "teamdelete", "&7Du hast dein Team &caufgelöst&7.", "&7Du hast dein Team &caufgelöst&7.", "Nachricht an den Inhaber des gelöschten Teams."),
    TEAMLEAVEMEMBER(ConfigSections.MESSAGES, "teamleavemember", "&a%PLAYER% &7hat das Team &cverlassen&7.", "&a%PLAYER% &7hat das Team &cverlassen&7.", "Nachricht an ein Team Mitglied, wenn ein Spieler das Team verlässt. \n %PLAYER% = Verlassender Spieler"),
    TEAMLEAVEPLAYER(ConfigSections.MESSAGES, "teamleaveplayer", "&7Du hast das Team von&a %OWNER% &cverlassen&7.", "&7Du hast das Team von&a %OWNER% &cverlassen&7.", "Nachricht an den Spieler, welcher das Team verlassen hat \n %OWNER% = Inhaber des Teams"),
    TEAMKICKPLAYER(ConfigSections.MESSAGES, "teamkickplayer", "&7Du wurdest aus dem Team von&a %OWNER% &centfernt&7.", "&7Du wurdest aus dem Team von&a %OWNER% &centfernt&7.", "Nachricht an den Spieler, welcher aus dem Team entfernt wird. \n %OWNER% = Inhaber des Teams"),
    TEAMPLAYERNOTATTEAM(ConfigSections.MESSAGES, "teamplayernotatteam", "&cDer Spieler %PLAYER% ist nicht in deinem Team.", "&cDer Spieler %PLAYER% ist nicht in deinem Team.", "Nachricht, wenn ein Spieler nicht in dem Team ist \n %PLAYER% = Gesuchter Spieler"),
    TEAMNOPERMTOKICK(ConfigSections.MESSAGES, "teamnopermtokick", "&cDu hast nicht die Berechtigung andere aus dem Team zu kicken.", "&cDu hast nicht die Berechtigung andere aus dem Team zu kicken.", "Nachricht an einen Spieler, welcher keine Rechte zum kicken aus einem hat."),
    TEAMKICKHELP(ConfigSections.MESSAGES, "teamkickhelp", "&cBitte nutze /team kick <Player>", "&cBitte nutze /team kick <Player>", "Hilfe für den Command /team kick"),
    TEAMHELP(ConfigSections.MESSAGES, "teamhelp", "&aInformationen zum Teamsystem \n &a/team invite &7<&aSpieler&7> &7Lädt einen Spieler in das Team ein \n &a/team accept &7Nimmt eine Anfrage an \n &a/team deny &7Lehnt eine Anfrage ab \n &a/team info &7Zeigt alle Mitglieder deines Teams an \n &a/team leave &7Verlässt das Team \n &a/team kick &7<&aSpieler&7> Kickt einen Spieler aus dem Team", "&aInformationen zum Teamsystem \n &a/team invite &7<&aSpieler&7> &7Lädt einen Spieler in das Team ein \n &a/team accept &7Nimmt eine Anfrage an \n &a/team deny &7Lehnt eine Anfrage ab \n &a/team info &7Zeigt alle Mitglieder deines Teams an \n &a/team leave &7Verlässt das Team \n &a/team kick &7<&aSpieler&7> Kickt einen Spieler aus dem Team", "Hilfe NAchricht beim Command /team"),

    //TEAMSTATES
    TEAMSTATEALLOWED(ConfigSections.TEAMSTATES, "allowed", "&aTeams erlaubt", "&aTeams erlaubt", "Prefix des TeamStates Allowed"),
    TEAMSTATEDISALLOWED(ConfigSections.TEAMSTATES, "disallowed", "&cTeams verboten", "&cTeams verboten", "Prefix des TeamStates Disallowed"),

    //SCOREBOARD
    SCOREBOARD(ConfigSections.SCOREBOARD, "scoreboard", "&8&M&l------------&2\n&6✦ &8▎ &7Map\n &8➥ &7%MAP%&a\n&8  \n&c✪ &8▎ &7Rekord\n &8➥ &7%RECORD%&c\n&d\n&c❤ &8▎ &7Kills\n &8➥ &7%KILLS%\n&1\n&c✿ &8▎ &7Rang\n &8➥ &7#%RANK%\n&8&M&l------------&3",
            "&8&M&l------------&2\n&6✦ &8▎ &7Map\n &8➥ &7%MAP%&a\n&8  \n&c✪ &8▎ &7Rekord\n &8➥ &7%RECORD%&c\n&d\n&c❤ &8▎ &7Kills\n &8➥ &7%KILLS%\n&1\n&c✿ &8▎ &7Rang\n &8➥ &7#%RANK%\n&8&M&l------------&3",
            "Scoreboard Einstellungen:\n %MAP% = Aktuelle Map/Arena \n %RECORD% = Highscore des Spielers \n %KILLS% = Alltime Kills eines Spielers \n %DEATHS% = Alltime Tode eines Spielers \n %RANK% = Rank eines Spielers \n %KD% = Kills/Deaths eines Spielers"),
    SCOREBOARDNAME(ConfigSections.SCOREBOARD, "name","&aGun&2Game", "&aGun&2Game", "Displayname des Scoreboards"),


    //DEATH
    PLAYERDEAD(ConfigSections.MESSAGES, "playerdead", "&7Du bist gestorben.", "&7Du bist gestorben.", "Nachricht wenn ein Spieler gestorben ist"),
    PLAYERKILLED(ConfigSections.MESSAGES, "playerkilled", "&7Du wurdest von &c %KILLER% &7getötet.", "&7Du wurdest von &c %KILLER% &7getötet.", "Nachricht an einen Spieler, wenn er getötet wurde. \n %KILLER% = Name des Killers"),
    KILLERKILLS(ConfigSections.MESSAGES, "killerkills", "&7Du hast&a %PLAYER% &7getötet.", "&7Du hast&a %PLAYER% &7getötet.", "Nachricht an einen Spieler, wenn er eienen anderen getötet hat. \n %PLAYER% = Getöteter Spieler"),

    //MAPCHANGE
    MAPCHANGE(ConfigSections.MESSAGES, "mapchange", "&7Die Map wurde auf&a %MAP% &7gesetzt.", "&7Die Map wurde auf&a %MAP% &7gesetzt.", "Nachricht wenn eine Map geändert wird."),
    MAPCHANGETIMER(ConfigSections.MESSAGES, "mapchangetimer", "&7Die Map wechselt in &a%SECONDS%-Sekunden&7.", "&7Die Map wechselt in &a%SECONDS%-Sekunden&7.", "Nachricht wenn der MapChange Counter einen bestimmten Wert erreicht."),
    MAPCHANGECOUNTER(ConfigSections.SETTINGS, "mapchangecounter", 30, 30,"Zeit wie lange eine Map in Minuten da ist, bis sie gewächselt wird"),

    GAMEACTIONBARMAPCHANGE(ConfigSections.MESSAGES, "gameactionbarmapchange", "&7Mapchange in&8: &a%SECONDS%-Sekunden &8&l︳ &7%TEAMSTATE%", "&7Mapchange in&8: &a%SECONDS%-Sekunden &8&l︳ &7%TEAMSTATE%", "Actionbar wenn der MapChange an ist."),
    GAMEACTIONBAR(ConfigSections.MESSAGES, "gameactionbar", "&7%TEAMSTATE%", "&7%TEAMSTATE%", "Actionbar wenn der MapChange aus ist."),

    //SETTINGS
    ANTICROPTRAMPLE(ConfigSections.SETTINGS, "anticroptrample", true, true, "Einstellung um kein Pflanzen zu zertramplen"),

    //MYSQL
    MYSQL_HOST(ConfigSections.MYSQL, "host", "localhost", "localhost", "Hostname deiner MySQl Datenbank"),
    MYSQL_PASSWORD(ConfigSections.MYSQL, "password", "password", "password", "Passwort deiner MySQl Datenbank"),
    MYSQL_DATABASE(ConfigSections.MYSQL, "database", "gungame", "gungame", "Zu benutzende Datenbank von MySQl"),
    MYSQL_PORT(ConfigSections.MYSQL, "port", 3306, 3306, "Port deiner MySQl Datenbank"),
    MYSQL_USERNAME(ConfigSections.MYSQL, "username", "root", "root", "Username deiner MySQl Datenbank");



    private ConfigSections section;
    private String path,desc;
    private Object value, defaultValue;
    ConfigEntries(ConfigSections section, String path, Object value, Object defaultValue, String desc){
        this.section = section;
        this.path = path;
        this.value = value;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }

    public int getAsInt(){
        return (int) getValue();
    }

    public String getAsString(){
        return getValue().toString().replaceAll("&", "§");
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public ConfigSections getSection() {
        return this.section;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
