package babysitter

import org.junit.Test
import static org.junit.Assert.assertEquals


class BabysitterIntTest {

    @Test
    public void whenBabysitterWorksShiftInAllThreePayScalesSheGetsPaidAppropriately() {
        Job job = new Job()
        job.work(10, 2, 11)
        PayCalculator payCalculator = new PayCalculator(job)

        assertEquals(1 * 12 + 1 * 8 + 2 * 16, payCalculator.calcPayment())
    }
}
