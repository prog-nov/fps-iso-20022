package com.forms.datapipe.config.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FieldDefinition
    implements Cloneable
{
    @XmlElement(name = "field")
    private List<Field> fields;

    public List<Field> getFields()
    {
        return fields;
    }

    public void setFields(List<Field> fields)
    {
        this.fields = fields;
    }

    public Field getField(String name)
    {
        for (Field field : fields)
        {
            if (field.getName().equals(name)) return field;
        }
        return null;
    }

    @Override
    protected Object clone()
    {
        FieldDefinition clone = new FieldDefinition();
        if (fields != null)
        {
            List<Field> newFields = new ArrayList<Field>();
            clone.setFields(newFields);
            for (Field field : fields)
            {
                newFields.add(Field.clone(field));
            }
        }
        return clone;
    }

    public static FieldDefinition clone(FieldDefinition source)
    {
        if (source == null)
        {
            return null;
        }
        return (FieldDefinition) source.clone();
    }

}
