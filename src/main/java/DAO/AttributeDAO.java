package DAO;

import entity.Attribute;
import entity.Personage;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 2:29 PM
 */
public interface AttributeDAO {
    public void addAttribute(Attribute attribute);

    public void updateAttribute(Attribute attribute);

    public Attribute getAttributeById(int attributeId);

    public Attribute getAttributeByName(String attributeName);

    public List<Attribute> getAllAttributes();

    public void deleteAttribute(Attribute attribute);

    public List<Attribute> getAttributesByPersonage(Personage personage);
}
