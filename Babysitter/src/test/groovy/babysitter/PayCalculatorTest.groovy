package babysitter

import spock.lang.Specification

import static org.junit.Assert.assertEquals

class PayCalculatorTest extends Specification {

    Job job;
    PayCalculator subject;

    def setup() {
        job = Mock(Job)
        subject = new PayCalculator(job)
    }


    def "when shift is before bedtime payment gets calculated correctly for hours worked"() {
        setup:
        job.hoursBeforeBed() >> 2

        when:
        subject.calcPayment()

        then:
        assertEquals(24, subject.calcPayment())
    }


    def "when shift is after bedtime payment gets calculated correctly for hours worked"() {
        setup:
        job.hoursAfterBed() >> 2

        when:
        subject.calcPayment()

        then:
        assertEquals(16, subject.calcPayment())
    }

    def "when shift is after midnight payment gets calculated correctly for hours worked"() {
        setup:
        job.hoursAfterMidnight() >> 3

        when:
        subject.calcPayment()

        then:
        assertEquals(48, subject.calcPayment())
    }

    def "when working shift before and after bedtime both pay rates are applied"() {
        setup:
        job.hoursBeforeBed() >> 2
        job.hoursAfterBed() >> 1

        when:
        subject.calcPayment()

        then:
        assertEquals(32, subject.calcPayment())
    }

    def "when working shift before, after bedtime, and after midnight all three rates are applied"() {
        setup:
        job.hoursBeforeBed() >> 2
        job.hoursAfterBed() >> 1
        job.hoursAfterMidnight() >> 1

        when:
        subject.calcPayment()

        then:
        assertEquals(48, subject.calcPayment())
    }
}
