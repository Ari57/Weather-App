const express = require("express")
const bodyParser = require("body-parser")
const app = express()
var path = require("path")
const socketio = require("socket.io")
const server = app.listen(8080, () => {
    console.log("Server is running")
})
const io = socketio(server)

io.on("connection", (socket) => {
    console.log("Connected to client")
})

var urlencodedParser = bodyParser.urlencoded({ extended: false});

app.use(express.static('public'));
app.get("/success", function (req, res) {
    res.sendFile(path.join(__dirname, '../public', 'success.html'));
})

app.get("/fail", function (req, res) {
  res.sendFile(path.join(__dirname, '../public', 'fail.html'));
})

app.post('/location', urlencodedParser, (req, res) => {

    io.on("error", (error) => {
    res.redirect("/fail")
    throw err
  })

    io.emit("message", req.body.locationName)
    res.redirect("/success")  
  })


app.listen(3000, () => {
    console.log("App running on port 3000")
})