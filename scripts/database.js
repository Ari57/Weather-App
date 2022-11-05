const lib = require("./credentials.js")

lib.client.connect()

lib.client.query("SELECT * FROM JOURNEYLOCATIONS", (error, result) => {
    if (!error) {
        console.log(result.rows)
    } else {
        console.log(error.message)
    }
    lib.client.end
})