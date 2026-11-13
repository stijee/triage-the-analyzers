package team.projectpulse.security;

import team.projectpulse.instructor.converter.InstructorToInstructorDtoConverter;
import team.projectpulse.student.Student;
import team.projectpulse.student.converter.StudentToStudentDtoConverter;
import team.projectpulse.user.MyUserPrincipal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    JwtProvider jwtProvider;
    @Mock
    StudentToStudentDtoConverter studentToStudentDtoConverter;
    @Mock
    InstructorToInstructorDtoConverter instructorToInstructorDtoConverter;

    @InjectMocks
    AuthService authService;

    @Test
    void testCreateLoginInfoDoesNotThrowWhenRolesAreNull() {
        Student student = new Student("j.smith", "John", "Smith", "j.smith@abc.edu", "123456", true, null);
        MyUserPrincipal principal = new MyUserPrincipal(student);
        // Pass the authorities explicitly: MyUserPrincipal.getAuthorities() would NPE on null roles, which is a separate matter.
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, List.of());

        assertThatCode(() -> this.authService.createLoginInfo(authentication)).doesNotThrowAnyException();

        Map<String, Object> loginInfo = this.authService.createLoginInfo(authentication);
        assertThat(loginInfo).doesNotContainKey("token");
    }

}
