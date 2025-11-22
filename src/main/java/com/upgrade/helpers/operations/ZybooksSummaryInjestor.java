package com.upgrade.helpers.operations;

import com.upgrade.config.CourseConfig;
import com.upgrade.helpers.XMLHelper;
import com.upgrade.model.general.Course;
import com.upgrade.model.general.GradeCategory;
import com.upgrade.model.general.Section;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.LinkedList;

public class ZybooksSummaryInjestor {
    public static ArrayList<Course> parseCourses() {
        //TODO injest grades
        Node rootNode = XMLHelper.getConfigRoot();

        ArrayList<Course> courses = parseCourses(rootNode);
    }

    private static Node getChild(Node node, String name) {
        return XMLHelper.getChild(node, name);
    }

    public static LinkedList<Node> getChildren(Node node, String name) {
        return XMLHelper.getChildren(node, name);
    }

    private static ArrayList<Course> parseCourses(Node rootNode) {
        ArrayList<Course> courses = new ArrayList<>();

        for(Node courseNode : getChildren(rootNode, "course")) {
            Course course = new Course();
            course.setCourseConfig(parseCourseConfig(courseNode));
            course.getCourseConfig().setCourse(course);

            course.setSections(parseSections(courseNode));

            courses.add(course);
        }

        Course.setCourses(courses);
        return courses;
    }

    private static CourseConfig parseCourseConfig(Node node) {
        CourseConfig courseConfig = new CourseConfig();
        NodeList childNodes = node.getChildNodes();

        Node courseConfigNode = getChild(node, "course_config");
        assert courseConfigNode != null;

        Node gradeCategoriesNode = getChild(courseConfigNode, "grade_categories");
        assert gradeCategoriesNode != null;

        courseConfig.setGradeCategories(parseGradeCategories(gradeCategoriesNode));

        parseZybooksConfig(courseConfig, courseConfigNode);

        return courseConfig;
    }



    private static ArrayList<GradeCategory> parseGradeCategories(Node courseConfigNode) {
        ArrayList<GradeCategory> gradeCategories = new ArrayList<>();

        for(Node gradeCategoryNode : getChildren(courseConfigNode, "grade_category")) {
            GradeCategory gradeCategory = new GradeCategory();
            NamedNodeMap attributes = gradeCategoryNode.getAttributes();

            gradeCategory.setName(attributes.getNamedItem("name").getNodeValue());
            gradeCategory.setWeightPercentage(Double.parseDouble(
                    attributes.getNamedItem("weight_percentage").getNodeValue()));

            gradeCategories.add(gradeCategory);
        }

        return gradeCategories;
    }

    private static void parseZybooksConfig(CourseConfig courseConfig, Node courseConfigNode) {
        Node zyConfignode = getChild(courseConfigNode, "course_zy_config");
        assert zyConfignode != null;

        Node gradeStartNode = getChild(zyConfignode, "grade_start_column");
        assert gradeStartNode != null;
        courseConfig.setGradeStartColumnZybooks(gradeStartNode.getAttributes().getNamedItem("column").getNodeValue());

        Node lastNameNode = getChild(zyConfignode, "last_name_column");
        assert gradeStartNode != null;
        courseConfig.setLastNameColumnZybooks(lastNameNode.getAttributes().getNamedItem("column").getNodeValue());

        Node firstNameNode = getChild(zyConfignode, "first_name_column");
        assert gradeStartNode != null;
        courseConfig.setFirstNameColumnZybooks(firstNameNode.getAttributes().getNamedItem("column").getNodeValue());

        Node idNode = getChild(zyConfignode, "id_column");
        assert gradeStartNode != null;
        courseConfig.setIdColumnZybooks(idNode.getAttributes().getNamedItem("column").getNodeValue());
    }

    private static ArrayList<Section> parseSections(Node courseNode) {
        ArrayList<Section> sections = new ArrayList<>();

        //TODO - parse sections

        return sections;
    }
}
