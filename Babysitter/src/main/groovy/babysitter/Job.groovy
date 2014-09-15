package babysitter


class Job {
    int startTime
    int endTime
    int bedTime

    def work(int fromTime, int toTime, int bedTime) {
        this.startTime = adjustForMidnight(fromTime)
        this.endTime = adjustForMidnight(toTime)
        this.bedTime = adjustForMidnight(bedTime)
    }

    static int adjustForMidnight(hour) {
        (hour >= 5) ? hour : 12 + hour
    }

    int hoursBeforeBed() {
        Math.min(endTime, bedTime) - startTime
    }

    int hoursAfterBed() {
        endTime - Math.max(startTime, bedTime) - hoursAfterMidnight()
    }

    int hoursAfterMidnight() {
        Math.max(0, endTime - 12) - Math.max(0, startTime - 12)
    }
}
