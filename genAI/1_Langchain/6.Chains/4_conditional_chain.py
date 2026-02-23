from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import StrOutputParser, PydanticOutputParser
from langchain_core.runnables import RunnableParallel, RunnableBranch
from pydantic import BaseModel, Field
from typing import Literal

class Feedback(BaseModel):
    sentiment: Literal['positive','negative'] = Field(description='Give the sentiment of Feedback')

load_dotenv()

model = ChatOpenAI()
parser = StrOutputParser()
parser_2 = PydanticOutputParser(pydantic_object=Feedback)

feedback = """
The iPhone 17 delivers an exceptional experience with its blazing-fast performance, stunning display, and long-lasting battery life. The refined design feels premium, and the camera upgrades let me capture crisp, vibrant photos in every situation. iOS runs smoothly, and the new features make daily use effortless and truly enjoyable. Overall, it’s one of the best iPhones yet!
"""


prompt1 = PromptTemplate(
    template='Classify sentiment onto positive or negative \n {feedback} \n{format_instruction} ',
    input_variables=['feedback'],
    partial_variables={'format_instruction':parser_2.get_format_instructions()}
)

classifier_chain = prompt1 | model | parser_2

prompt2 = PromptTemplate(
    template='Write a appropriate respone to this positive feedback \n {feedback}',
    input_variables=['feedback'],
)


prompt3 = PromptTemplate(
    template='Write a appropriate respone to this negative feedback \n {feedback}',
    input_variables=['feedback'],
)
branch_chain = RunnableBranch(
    (lambda x:x.sentiment=='positive', prompt2 | model | parser),
    (lambda x:x.sentiment=='negative', prompt3 | model | parser),
    lambda x: "could not find sentiment"
)

chain = classifier_chain | branch_chain

result = chain.invoke({'feedback': feedback})
print(result)