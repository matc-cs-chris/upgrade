package com.upgrade.config;

import com.upgrade.model.classroom.Section;

import java.util.ArrayList;

public class SectionConfig {
    private Section section;
    private ArrayList<String> usernameOrderBrightspace;

    //Getters/Setters Below

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public ArrayList<String> getUsernameOrderBrightspace() {
        return usernameOrderBrightspace;
    }

    public void setUsernameOrderBrightspace(ArrayList<String> usernameOrderBrightspace) {
        this.usernameOrderBrightspace = usernameOrderBrightspace;
    }
}
