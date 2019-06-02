package mca.api.types;

import mca.core.MCA;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Class Gift is an item
 */
public class Gift {
    private String type;
    private String name;
    private int value;

    public Gift(String type, String name, int value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public boolean exists() {
        if (getType().equals("block")) {
            return Block.getBlockFromName(getName()) != null;
        } else if (getType().equals("item")) {
            return Item.getByNameOrId(getName()) != null;
        } else {
            MCA.getLog().warn("Could not process gift '" + getName() + "'- bad type name of '" + getType() + "'. Must be 'item' or 'block'");
            return false;
        }
    }
}