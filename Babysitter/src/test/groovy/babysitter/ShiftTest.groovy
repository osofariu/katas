package babysitter
import spock.lang.Specification

class ShiftTest extends Specification {

    Job job = Mock(Job)
    Shift subject

    def "hours are allocated to shift before bed"() {
        setup:
        job.startTime >> 8
        job.endTime >> 10
        job.bedTime >> 10

        when:
        subject = new BeforeBedShift(job)

        then:
        assert subject.calcHours() == 2
    }

    def "hours are allocated to shift after bed"() {
        setup:
        job.startTime >> 8
        job.endTime >> 11
        job.bedTime >> 8

        when:
        subject = new AfterBedShift(job)

        then:
        assert subject.calcHours() == 3
    }

    def "hours are allocated to shifts before and after midnight"() {
        setup:
        job.startTime >> 11
        job.endTime >> 13
        job.bedTime >> 11

        when:
        Shift afterMidnightShift = new AfterMidnightShift(job)
        Shift afterBedShift = new AfterBedShift(job)

        then:
        assert afterMidnightShift.calcHours() == 1
        assert afterBedShift.calcHours() == 1
    }


    def "when no hours are worked before bed that shift is zero hours"() {
        setup:
        job.startTime >> 5
        job.endTime >> 7
        job.bedTime >> 5

        when:
        subject = new BeforeBedShift(job)

        then:
        assert subject.calcHours() == 0
    }


    def "hours after midnight are not included in shift after bed"() {
        setup:
        job.startTime >> 10
        job.endTime >> 14
        job.bedTime >> 11

        when:
        subject = new AfterBedShift(job)

        then:
        assert subject.calcHours() == 1
    }

    def "hours before bed get paid as 12 dollars hourly rate"() {
        setup:
        subject = new BeforeBedShift(job)

        expect:
        assert subject.hourlyRate() == 12
    }

    def "hours after bed (and before midnight)get paid as 8 dollars hourly rate"() {
        setup:
        subject = new AfterBedShift(job)

        expect:
        assert subject.hourlyRate() == 8
    }

    def "hours after midnight get paid as 16 dollars hourly rate"() {
        setup:
        subject = new AfterMidnightShift(job)

        expect:
        assert subject.hourlyRate() == 16
    }
}
