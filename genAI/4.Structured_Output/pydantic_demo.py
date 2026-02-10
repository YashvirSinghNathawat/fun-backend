from pydantic import BaseModel, EmailStr, Field
from typing import Optional

class Student(BaseModel):
    name: str
    age: Optional[int] = 32
    phone: Optional[str] = None
    email: EmailStr                # Python Coerce
    cgpa: float = Field(gt=0, lt=10, default = 5, description="CGPA of student")

new_student = {
    'name': 'yash',
    'age':  32,  # Coerce
    'email': 'abv@gmail.com',
    'cgpa': 5
}

student = Student(**new_student)

print(student)
print(student.name)