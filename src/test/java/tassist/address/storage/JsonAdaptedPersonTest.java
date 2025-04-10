package tassist.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tassist.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static tassist.address.testutil.Assert.assertThrows;
import static tassist.address.testutil.TypicalPersons.BENSON;
import static tassist.address.testutil.TypicalTimedEvents.ASSIGNMENT_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import tassist.address.commons.exceptions.IllegalValueException;
import tassist.address.model.person.ClassNumber;
import tassist.address.model.person.Email;
import tassist.address.model.person.Name;
import tassist.address.model.person.Phone;
import tassist.address.model.person.Progress;
import tassist.address.model.person.ProjectTeam;
import tassist.address.model.person.Repository;
import tassist.address.model.person.StudentId;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CLASS = "T100";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PROGRESS = "150";
    private static final String INVALID_STUDENT_ID = "A0C";
    private static final String INVALID_PROJECT_TEAM = "";
    private static final String INVALID_REPOSITORY = ".repo";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_CLASS = BENSON.getClassNumber().toString();
    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_GITHUB = BENSON.getGithub().toString();
    private static final String VALID_PROJECT_TEAM = BENSON.getProjectTeam().toString();
    private static final String VALID_REPOSITORY = BENSON.getRepository().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_PROGRESS = String.valueOf(BENSON.getProgress().value);
    private static final List<JsonAdaptedTimedEvent> VALID_TIMED_EVENTS = new ArrayList<>();
    private static final List<JsonAdaptedTimedEvent> INVALID_TIMED_EVENTS = new ArrayList<>();

    static {
        VALID_TIMED_EVENTS.add(new JsonAdaptedTimedEvent(ASSIGNMENT_1));
        INVALID_TIMED_EVENTS.add(new JsonAdaptedTimedEvent("Invalid Name", "Description",
                LocalDateTime.now().toString(), "InvalidType"));
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidClassNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = ClassNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullClassNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, INVALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, null, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidProjectTeam_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, INVALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = ProjectTeam.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullProjectTeam_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, null, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProjectTeam.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRepository_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, INVALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = Repository.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRepository_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, null,
                VALID_TAGS, VALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Repository.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                invalidTags, VALID_PROGRESS, VALID_TIMED_EVENTS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidProgress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, INVALID_PROGRESS, VALID_TIMED_EVENTS);
        String expectedMessage = Progress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullProgress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, null, VALID_TIMED_EVENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Progress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTimedEvents_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, INVALID_TIMED_EVENTS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullTimedEvents_returnsEmptyList() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_CLASS, VALID_STUDENT_ID, VALID_GITHUB, VALID_PROJECT_TEAM, VALID_REPOSITORY,
                VALID_TAGS, VALID_PROGRESS, null);
        assertEquals(0, person.toModelType().getTimedEvents().size());
    }
}
