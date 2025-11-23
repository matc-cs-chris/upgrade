package com.upgrade.operations;

import com.upgrade.config.CourseConfig;
import com.upgrade.config.SectionConfig;
import com.upgrade.helpers.XMLHelper;
import com.upgrade.model.classroom.Course;
import com.upgrade.model.classroom.GradeCategory;
import com.upgrade.model.classroom.Section;
import com.upgrade.model.classroom.Student;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ConfigIngestor {
    public static boolean usePercentage = false;

    public static LinkedList<Course> parseCourses() {
        Node rootNode = XMLHelper.getConfigRoot();

        LinkedList<Course> courses = parseCourses(rootNode);

        return courses;
    }

    private static Node getChild(Node node, String name) {
        return XMLHelper.getChild(node, name);
    }

    public static LinkedList<Node> getChildren(Node node, String name) {
        return XMLHelper.getChildren(node, name);
    }

    private static LinkedList<Course> parseCourses(Node rootNode) {
        LinkedList<Course> courses = new LinkedList<>();

        if (Course.getCourses() == null) Course.setCourses(new LinkedList<>());

        Node coursesNode = getChild(rootNode, "courses");

        for(Node courseNode : getChildren(coursesNode, "course")) {
            Course course = new Course();
            course.setName(courseNode.getAttributes().getNamedItem("name").getNodeValue());

            //Course config setup
            course.setCourseConfig(parseCourseConfig(course, courseNode));

            //Section setup
            course.setSections(parseSections(course, courseNode));

            courses.add(course);
            Course.getCourses().add(course);
        }

        return courses;
    }

    private static CourseConfig parseCourseConfig(Course course, Node node) {
        CourseConfig courseConfig = new CourseConfig();
        NodeList childNodes = node.getChildNodes();

        Node courseConfigNode = getChild(node, "course_config");
        assert courseConfigNode != null;

        Node gradeCategoriesNode = getChild(courseConfigNode, "grade_categories");
        assert gradeCategoriesNode != null;

        courseConfig.setGradeCategories(parseGradeCategories(gradeCategoriesNode));

        courseConfig.setCourse(course);

        parseZybooksConfig(courseConfig, courseConfigNode);

        return courseConfig;
    }



    private static LinkedList<GradeCategory> parseGradeCategories(Node courseConfigNode) {
        LinkedList<GradeCategory> gradeCategories = new LinkedList<>();

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

    private static LinkedList<Section> parseSections(Course course, Node courseNode) {
        LinkedList<Section> sections = new LinkedList<>();

        if(Section.getSections() == null) Section.setSections(new LinkedList<>());

        Node sectionsNode = getChild(courseNode, "sections");

        for(Node sectionNode : getChildren(sectionsNode, "section")) {
            Section section = new Section();

            //could add asserts to check name exists
            section.setName(sectionNode.getAttributes().getNamedItem("name").getNodeValue());

            section.setSectionConfig(parseSectionConfig(section, sectionNode));
            assert section.getSectionConfig() != null;

            section.setStudents(parseStudents(section, sectionNode));

            section.setCourse(course);

            sections.add(section);
            Section.getSections().add(section);
        }

        course.setSections(sections);

        return sections;
    }

    private static SectionConfig parseSectionConfig(Section section, Node sectionNode) {
        SectionConfig sectionConfig = new SectionConfig();

        Node sectionConfigNode = getChild(sectionNode, "section_config");
        assert sectionConfigNode != null;

        Node sectionConfigBrightspaceNode = getChild(sectionConfigNode, "section_bs_config");
        assert sectionConfigBrightspaceNode != null;

        Node usernameOrderBrightspaceNode = getChild(sectionConfigBrightspaceNode, "username_order");
        assert usernameOrderBrightspaceNode != null;

        String usernameOrder = usernameOrderBrightspaceNode.getTextContent();
        String usernameOrderNoWhitespace = usernameOrder.replaceAll("\\s*", "");
        assert !usernameOrderNoWhitespace.equals("");

        if(sectionConfig.getUsernameOrderBrightspace() == null) {
            sectionConfig.setUsernameOrderBrightspace(new ArrayList<>());
        }

        StringTokenizer tokenizer = new StringTokenizer(usernameOrderNoWhitespace, ",");
        while(tokenizer.hasMoreTokens()) {
            sectionConfig.getUsernameOrderBrightspace().add(tokenizer.nextToken());
        }

        sectionConfig.setSection(section);

        return sectionConfig;
    }

    private static LinkedList<Student> parseStudents(Section section, Node sectionNode) {
        LinkedList<Student> students = new LinkedList<>();

        if(Student.getStudents() == null) Student.setStudents(new LinkedList<>());

        Node studentsNode = getChild(sectionNode, "students");
        assert studentsNode != null;

        for(Node studentNode : getChildren(studentsNode, "student")) {
            Student student = new Student();

            student.setUsernameBrightSpace(studentNode.getAttributes().getNamedItem("bs_username").getNodeValue());
            assert student.getUsernameBrightSpace() != null && !student.getUsernameBrightSpace().equals("");
            student.setIdZybooks(studentNode.getAttributes().getNamedItem("zy_id").getNodeValue());
            assert student.getIdZybooks() != null && !student.getIdZybooks().equals("");

            if(section.getStudents() == null) { section.setStudents(new LinkedList<>()); }

            student.setSection(section);

            students.add(student);
            Student.getStudents().add(student);
        }

        return students;
    }
}
