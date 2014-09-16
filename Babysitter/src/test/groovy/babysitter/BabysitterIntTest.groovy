package babysitter
import org.junit.Test
import spock.lang.Specification

class BabysitterIntTest extends Specification {

    @Test
    public void "when babysitter works all payRates the payment gets computed correctly"() {
        when:
        Job job = new Job(startTime: 10, endTime: 2, bedTime: 11)

        then:
        assert (job.pay() == 1 * 12 + 1 * 8 + 2 * 16)
    }
}
