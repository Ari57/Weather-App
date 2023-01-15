const express = require("express")
const bodyParser = require("body-parser")
const app = express()
var path = require("path")
const socketio = require("socket.io")
const server = app.listen(8080, () => {
    console.log("Server is running on port 8080")
})
const io = socketio(server)


io.on("connection", socket => {
  console.log("A client connected");
  
  socket.on("message", message => {
    console.log("Received message from client: " + message);
  });
  
});

var urlencodedParser = bodyParser.urlencoded({ extended: false});

app.use(express.static("public"));
app.get("/success", function (req, res) {
    res.sendFile(path.join(__dirname, "../public", "success.html"));
})

app.get("/fail", function (req, res) {
  res.sendFile(path.join(__dirname, "../public", "fail.html"));
})

app.post("/location", urlencodedParser, (req, res) => {

    io.on("error", (err) => {
    res.redirect("/fail")
    throw err
  })

    io.emit("msg", req.body.locationName)
    res.redirect("/success")  
  })

app.listen(3000, () => {
})    