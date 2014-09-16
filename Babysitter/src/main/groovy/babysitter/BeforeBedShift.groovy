package babysitter

class BeforeBedShift implements Shift {

    Job job

    BeforeBedShift(job) {
        this.job = job
    }

    @Override
    int hourlyRate() {
        12
    }

    @Override
    int calcHours() {
         Math.min(12, Math.min(job.endTime, job.bedTime)) - job.startTime
    }
}
