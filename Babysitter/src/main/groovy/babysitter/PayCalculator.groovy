package babysitter


class PayCalculator {

    def PAY_BEFORE_BED = 12
    def PAY_AFTER_BED = 8
    def PAY_AFTER_MIDNIGHT = 16
    Job job

    PayCalculator(Job job) {
        this.job = job
    }

    def calcPayment() {
        PAY_BEFORE_BED * job.hoursBeforeBed() + PAY_AFTER_BED * job.hoursAfterBed() + PAY_AFTER_MIDNIGHT * job.hoursAfterMidnight()
    }
}
