package babysitter


class Job {
    def startTime, endTime, bedTime

    def getEndTime() {
        adjustForMidnight(endTime)
    }

    def getStartTime() {
        adjustForMidnight(startTime)
    }

    def getBedTime() {
        adjustForMidnight(bedTime)
    }

    static int adjustForMidnight(hour) {
        (hour >= 5) ? hour : 12 + hour
    }

    def pay() {
        makeShifts().inject(0) { acc, shift ->
            acc + shift.calcHours() * shift.hourlyRate()
        }
    }

    List makeShifts() {
        def shifts = []
        shifts.add(new BeforeBedShift(this))
        shifts.add(new AfterBedShift(this))
        shifts.add(new AfterMidnightShift(this))
        shifts
    }
}
