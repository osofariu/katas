package babysitter


class AfterBedShift implements Shift {

    Job job

    AfterBedShift(job) {
        this.job = job
    }

    @Override
    int hourlyRate() {
        8
    }

    @Override
    int calcHours() {
        Math.min(12, job.endTime) - Math.min(12, Math.max(job.startTime, job.bedTime))
    }
}
