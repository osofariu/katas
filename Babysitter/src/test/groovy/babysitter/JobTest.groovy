package babysitter
import spock.lang.Specification

class JobTest extends Specification {

    Job subject

    def setup() {
        subject = new Job()
    }


    def "when shift is before bedtime payment gets calculated correctly for hours worked"() {
        setup:
        subject.startTime = 8
        subject.endTime = 10
        subject.bedTime = 10

        expect:
        assert subject.pay() == 24
    }


    def "when shift is after bedtime payment gets calculated correctly for hours worked"() {
        setup:
        subject.startTime = 8
        subject.endTime = 10
        subject.bedTime = 8

        expect:
        assert subject.pay() == 16
    }

    def "when shift is after midnight payment gets calculated correctly for hours worked"() {
        setup:
        subject.startTime = 12
        subject.endTime = 3
        subject.bedTime = 12

        expect:
        assert subject.pay() == 48
    }

    def "when shift and bedtime are after midnight payment gets calculated correctly for hours worked"() {
        setup:
        subject.startTime = 12
        subject.endTime = 3
        subject.bedTime = 3

        expect:
        assert subject.pay() == 48
    }

    def "when working shift before and after bedtime both pay rates are applied"() {
        setup:
        subject.startTime = 6
        subject.endTime = 9
        subject.bedTime = 8

        expect:
        assert subject.pay() == 32
    }

    def "when working shift before, after bedtime, and after midnight all three rates are applied"() {
        setup:
        subject.startTime = 9
        subject.endTime = 1
        subject.bedTime = 11

        expect:
        assert subject.pay() == 48
    }
}
