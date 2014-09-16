package babysitter


class AfterMidnightShift implements Shift {

    Job job

    AfterMidnightShift(job) {
        this.job = job
    }

    @Override
    int hourlyRate() {
        16
    }

    @Override
    int calcHours() {
        Math.max(0, job.endTime - 12) - Math.max(0, job.startTime - 12)
    }
}
