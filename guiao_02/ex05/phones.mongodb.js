/* global use, db */
// MongoDB Playground
// To disable this template go to Settings | MongoDB | Use Default Template For Playground.
// Make sure you are connected to enable completions and to be able to run a playground.
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.
// The result of the last command run in a playground is shown on the results panel.
// By default the first 20 documents will be returned with a cursor.
// Use 'console.log()' to print to the debug output.
// For more documentation on playgrounds please refer to
// https://www.mongodb.com/docs/mongodb-vscode/playgrounds/

// Select the database to use.

populatePhones = function (country, start, stop) {

    var prefixes = [21, 22, 231, 232, 233, 234 ];
    for (var i = start; i <= stop; i++) {
  
        var prefix = prefixes[(Math.random() * 6) << 0]
        var countryNumber = (prefix * Math.pow(10, 9 - prefix.toString().length)) + i;
        var num = (country * 1e9) + countryNumber;
        var fullNumber = "+" + country + "-" + countryNumber;
  
        db.phones.insert({
            _id: num,
            components: {
            country: country,
            prefix: prefix,
            number: i
        },
        display: fullNumber
    });
    print("Inserted number " + fullNumber);
    }
  print("Done!");
  }



//populatePhones(351, 1, 5)

//db.phones.drop()

//populatePhones(351, 1, 200000)

/*
db.phones.aggregate([
    {$group: {
        _id: "$components.prefix",
        totalPrefix: {$sum: 1}
    }}
])
*/



SequencesOfThree = function(phoneNumbers){
    const numbers = []
    while(phoneNumbers.hasNext()){
        let number = phoneNumbers.next().display.split("-")[1]
        for(let i = 0; i < number.length; i++){
            if (i + 2 <= number.length && number[i] != "0"){
                if ((number[i] == number[i + 1]) && (number[i] == number[i + 2])){
                    numbers.push(number);
                    break
                }
            }
        }
    }
    print(numbers)
}

SequencesOfThree(db.phones.find({}, {_id:0, display: 1}))
