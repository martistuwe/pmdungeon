package de.fhbielefeld.pmdungeon.game.characters.components;

import de.fhbielefeld.pmdungeon.game.items.Item;

/**
 * Inventory component of a character
 */
public class InventoryComponent {

    private final int size;
    private final Item[] items;
    private int selectedItem;

    public InventoryComponent(int size) {
        this.size = size;
        items = new Item[size];
    }

    /**
     * Removing an item from the inventory
     *
     * @param item Item that should be removed
     */
    public void removeItem(Item item) {
        if (item != null) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == item) {
                    item.dispose();
                    items[i] = null;
                }
            }
        }
    }

    /**
     * Adds an item to the inventory, if there is a slot for it
     *
     * @param item Item that should be added
     * @return true if it got added; false if there is no more free space in the inventory
     */
    public boolean add(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (Item item : items) {
            if (item != null) return false;
        }
        return true;
    }

    public Item getSelectedItem() {
        return items[selectedItem];
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSize() {
        return size;
    }

    public Item[] getItems() {
        return items;
    }
}
