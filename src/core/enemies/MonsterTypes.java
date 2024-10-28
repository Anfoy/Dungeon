package core.enemies;

public enum MonsterTypes {

    GOBLIN("A small, sneaky creature"),
    DRAGON("A large, fire-breathing beast"),
    SPIDER("A crawling, 8-legged creature"),
    FISHERMAN("A man who died making a living..."),
    LAVASAKE("A molten, slithering creature, ready to suffocate any foe...");

    private String description;

    // Constructor
    MonsterTypes(String description) {
        this.description = description;
    }

    // Getter method to access the description
    public String getDescription() {
        return description;
    }
}