package com.papatriz.daily.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.papatriz.daily.dto.ActivityDto;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.service.ActivityService;
import com.papatriz.daily.service.UserService;
import com.papatriz.daily.validator.ActivityRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ActivityControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private ActivityService activityService;
    @Mock
    private ActivityRequestValidator validator;
    @InjectMocks
    private ActivityController controller;

    @Test
    void testGetActivitiesForUser() {
        // Arrange
        User testUser = new User();
        testUser.setName("John");
        when(userService.getTestUser()).thenReturn(Optional.of(testUser));

        ActivityDto activityDto1 = new ActivityDto(0L,"Running", (short) 30, (short) 5);
        ActivityDto activityDto2 = new ActivityDto(0L, "Swimming", (short) 60, (short) 10);
        List<ActivityDto> activities = List.of(activityDto1, activityDto2);
        when(activityService.getActivitiesByUser(testUser)).thenReturn(activities);

        // Act
        List<ActivityDto> result = controller.getActivitiesForUser();

        // Assert
        assertThat(result).isEqualTo(activities);
    }

    @Test
    void testIsActivityComplete() {
        // Arrange
        long activityId = 123;
        LocalDate date = LocalDate.of(2020, 1, 1);
        when(activityService.isActivityComplete(activityId, date)).thenReturn(true);

        // Act
        boolean result = controller.isActivityComplete(activityId, date);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void testCreateActivity_ValidRequest() {
        // Arrange
        ActivityDto activityDto = new ActivityDto(0L,"Running", (short) 30, (short) 5);
        User testUser = new User();
        testUser.setName("John");
        when(userService.getTestUser()).thenReturn(Optional.of(testUser));
        when(validator.validate(activityDto)).thenReturn(List.of());

        // Act
        ResponseEntity<String> response = controller.createActivity(activityDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("{\"activityId\":\"null\"}");
    }

    @Test
    void testCreateActivity_InvalidRequest() {
        // Arrange
        ActivityDto activityDto = new ActivityDto(0L, "Running", (short) 30, (short) 5);
        when(validator.validate(activityDto)).thenReturn(List.of("error1", "error2"));

        // Act
        ResponseEntity<String> response = controller.createActivity(activityDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("error1\nerror2");
    }
}
