package com.upgrade.helpers.operations;

import com.upgrade.config.CourseConfig;
import com.upgrade.helpers.XMLHelper;
import com.upgrade.model.general.Course;
import com.upgrade.model.general.GradeCategory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class ZybooksSummaryInjestor {
    public static ArrayList<Course> parseCourses() {
        //TODO injest grades
        Node rootNode = XMLHelper.getConfigRoot();

        ArrayList<Course> courses = parseCourses(rootNode);
    }

    private static ArrayList<Course> parseCourses(Node rootNode) {
        NodeList nodes = rootNode.getOwnerDocument().getElementsByTagName("course");
        ArrayList<Course> courses = new ArrayList<>();


        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {
                if(node.getNodeName().equals("course")) {
                    Course course = new Course();
                    course.setCourseConfig(parseCourseConfig(node));
                    course.getCourseConfig().setCourse(course);
                    //TODO - finish course parsing

                    courses.add(course);
                }
            }
        }

        Course.setCourses(courses);
        return courses;
    }

    private static CourseConfig parseCourseConfig(Node node) {
        CourseConfig courseConfig = new CourseConfig();
        NodeList childNodes = node.getChildNodes();
        Node courseConfigNode = null;

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);

            if(childNode.getNodeType() == Node.ELEMENT_NODE) {
                if(childNode.getNodeName().equals("course_config")) {
                    courseConfigNode = childNode;
                }
            }
        }

        assert courseConfigNode != null;

        childNodes = courseConfigNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE) {
                if(childNode.getNodeName().equals("grade_categories")) {
                    courseConfig.setGradeCategories(parseGradeCategories(childNode));
                }
            }
        }
    }

    private static ArrayList<GradeCategory> parseGradeCategories(Node courseConfigNode) {
        ArrayList<GradeCategory> gradeCategories = new ArrayList<>();
        NodeList childNodes = courseConfigNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if(childNode.getNodeType() == Node.ELEMENT_NODE) {
                if(childNode.getNodeName().equals("grade_category")) {

                }
            }
        }
    }
}
