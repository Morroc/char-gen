package services;

import DAO.AttributeDAO;
import DAO.PersonageDAO;
import DAO.PersonageHasAttributeDAO;
import DAO.RaceHasAttributeDAO;
import entity.Attribute;
import entity.PersonageHasAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: artemk
 * Date: 8/16/14
 * Time: 7:18 PM
 */
@Service
public class AttributeService {
    @Autowired
    private AttributeDAO attributeDAO;

    @Autowired
    private PersonageDAO personageDAO;

    @Transactional
    public void addAttribute(Attribute attribute) {
        attributeDAO.addAttribute(attribute);
    }

    @Transactional
    public void updateAttribute(Attribute attribute) {
        attributeDAO.updateAttribute(attribute);
    }

    @Transactional
    public List<Attribute> getAllAttributes() {
        return attributeDAO.getAllAttributes();
    }

    @Transactional
    public void deleteAttributeById(int attributeId) {
        attributeDAO.deleteAttribute(attributeDAO.getAttributeById(attributeId));
    }

    @Transactional
    public Attribute getAttributeById(int attributeId) {
        return attributeDAO.getAttributeById(attributeId);
    }

    @Transactional
    public List<Attribute> getAttributesByPersonageId(int personageId) {
        return attributeDAO.getAttributesByPersonage(personageDAO.getPersonageById(personageId));
    }
}
