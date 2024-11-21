let lineReader = require('readline').createInterface({
    input: require('fs').createReadStream('Day1/input.txt')
});

lineReader.on('line', function (line) {
    console.log('Line from file: ', line);
});

lineReader.on('close', function () {
    console.log('good work today');
})
