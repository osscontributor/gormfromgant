import demo.Person

// demonstrate that the count method works...
def numberOfPeople = Person.count()
println "The number of people initially in the database: ${numberOfPeople}"

// demonstrate that the save method works...
['Jeff', 'Jake', 'Zack', 'Betsy'].each { name ->
    new Person(firstName: name).save(failOnError: true)
    println "Saved $name to the database."
}
numberOfPeople = Person.count()
println "The number of people in the database after saving those people: ${numberOfPeople}"

// demonstrate that dynamic finders work
def people = Person.findAllByFirstNameLike('J%')
println "The number of people in the database with a name beginning with 'J': ${people?.size()}"

println 'When the script terminates all of the people will be gone because the application is currently using an in memory database.'
