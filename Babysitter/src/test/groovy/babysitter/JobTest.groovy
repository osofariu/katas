package babysitter

import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals


class JobTest {

    Job subject

    @Before
    public void setUp() {
        subject = new Job()
    }

    @Test
    public void whenNoHoursAreWorkedBeforeBedtimeTheShiftIsZeroHours() {
        subject.work(5, 7, 5)

        assertEquals(0, subject.hoursBeforeBed())
    }

    @Test
    public void whenEndTimeAfterMidnightIsAdjustedCorrectly() {
        subject.work(10, 2, 10)

        assertEquals(2, subject.hoursAfterBed())
    }

    @Test
    public void whenStartTimeAfterMidnightItIsAdjustedCorrectly() {
        subject.work(2, 3, 10)

        assertEquals(0, subject.hoursAfterBed())
    }

    @Test
    public void whenBedTimeAfterMidnightItIsAdjustedCorrectly() {
        subject.work(2, 3, 4)

        assertEquals(1, subject.hoursBeforeBed())
    }

    @Test
    public void whenEndTimeAfterMidnightThatShiftIsCalculated() {
        subject.work(10, 2, 3)

        assertEquals(2, subject.hoursAfterMidnight())
    }

    @Test
    public void whenWorkingAfterMidnightThoseHoursAreNotIncludedInHoursAfterBed() {
        subject.work(10, 2, 11)

        assertEquals(1, subject.hoursAfterBed())
    }

}
