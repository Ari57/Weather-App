const express = require("express")
const bodyParser = require("body-parser")
const app = express()
const port = 3000
const db = require("./queries")

app.use(bodyParser.json())
app.use(
    bodyParser.urlencoded({
        extended: true,
    })
)

app.get("/", (request, response) => {
    response.json({info: "Node.js"})
})

app.get("/users", db.getUsers)
app.post("/users", db.addLocation)
app.post("/users/:id", db.updateUser)

app.listen(port, () => {
    console.log(`App running on port ${port}`)
})


