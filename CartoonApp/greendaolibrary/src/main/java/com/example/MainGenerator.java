package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.Entity;

public class MainGenerator {
    public static void main (String args[]) throws Exception  {
        final Schema schema = new Schema(1, "ca.bcit.cartoonapp.database.schema");
        final Entity table = schema.addEntity("Cartoon");

        table.addIdProperty ();

        table.addStringProperty ("name");
        table.addStringProperty ("pictureuri");

        new DaoGenerator ().generateAll (schema, "./app/src/main/java");
    }
}