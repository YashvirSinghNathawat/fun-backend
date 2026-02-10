from typing import TypedDict

class Person(TypedDict):
    name: str
    age: int
    
new_person: Person = {
    'name': 'yash',
    'age': '24'   # No validation in type dict
}



print(new_person)