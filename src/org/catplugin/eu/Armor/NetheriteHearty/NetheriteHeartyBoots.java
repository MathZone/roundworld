package org.catplugin.eu.Armor.NetheriteHearty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Main;

import java.util.Objects;
import java.util.UUID;

public class NetheriteHeartyBoots {
    public static void init(){

        ItemMeta heartyMeta = Main.netheriteHeartyBoots.getItemMeta();
        heartyMeta.setDisplayName(ChatColor.RED + "♥ Hearty Boots ♥");
        NamespacedKey heartyKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hearty");
        AttributeModifier hpModifier = new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(),"generic.armor",3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier toughModifier = new AttributeModifier(UUID.randomUUID(),"generic.armorToughness",3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier kbRes = new AttributeModifier(UUID.randomUUID(), "generic.knockbackResistance", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);

        heartyMeta.getPersistentDataContainer().set(heartyKey, PersistentDataType.INTEGER, 1);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE,kbRes);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,hpModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,armorModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,toughModifier);

        Main.netheriteHeartyBoots.setItemMeta(heartyMeta);


    }
}
