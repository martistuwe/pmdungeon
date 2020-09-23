package de.fhbielefeld.pmdungeon.game.inventory;

import de.fhbielefeld.pmdungeon.game.items.Item;

public class Inventory {

    private final int size;
    private final Item[] items;
    private int selectedItem;

    public Inventory(int size) {
        this.size = size;
        items = new Item[size];
    }

    public void setSlot(int index, Item item) {
        if (index >= 0 && index < items.length) {
            items[index] = item;
        }
    }

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

    public Item getSelectedItem() {
        return items[selectedItem];
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public boolean add(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Item[] getItems() {
        return items;
    }
}
