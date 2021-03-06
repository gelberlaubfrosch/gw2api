package me.nithanim.gw2api.v2.api.recipes;

import static me.nithanim.gw2api.v2.api.recipes.RecipeType.RecipeCategory.*;

public enum RecipeType {
    AXE(WEAPON), DAGGER(WEAPON), FOCUS(WEAPON), GREATSWORD(WEAPON),
    HAMMER(WEAPON), HARPOON(WEAPON), LONG_BOW(WEAPON), MACE(WEAPON),
    PISTOL(WEAPON), RIFLE(WEAPON), SCEPTER(WEAPON), SHIELD(WEAPON),
    SHORT_BOW(WEAPON), SPEARGUN(WEAPON), STAFF(WEAPON), SWORD(WEAPON),
    TORCH(WEAPON), TRIDENT(WEAPON), WARHORN(WEAPON),
    BOOTS(ARMOR), COAT(ARMOR), GLOVES(ARMOR), HELM(ARMOR), LEGGINGS(ARMOR),
    SHOULDERS(ARMOR),
    AMULET(TRINKET), EARRING(TRINKET), RING(TRINKET),
    DESSERT(FOOD), FEAST(FOOD), INGREDIENT_COOKING(FOOD), MEAL(FOOD),
    SEASONING(FOOD), SNACK(FOOD), SOUP(FOOD),
    COMPONENT(CRAFTING_COMPONENT), INSCRIPTION(CRAFTING_COMPONENT),
    INSIGNIA(CRAFTING_COMPONENT),
    REFINEMENT(RecipeCategory.REFINEMENT),
    REFINEMENT_ECTOPLASM(RecipeCategory.REFINEMENT),
    REFINEMENT_OBSIDIAN(RecipeCategory.REFINEMENT),
    BACKPACK(OTHER), BAG(OTHER), BULK(OTHER), CONSUMABLE(OTHER), DYE(OTHER),
    POTION(OTHER), UPGRADE_COMPONENT(OTHER);

    private final RecipeCategory category;

    private RecipeType(RecipeCategory category) {
        this.category = category;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public enum RecipeCategory {
        WEAPON, ARMOR, TRINKET, FOOD, CRAFTING_COMPONENT, REFINEMENT, OTHER;
    }
}
