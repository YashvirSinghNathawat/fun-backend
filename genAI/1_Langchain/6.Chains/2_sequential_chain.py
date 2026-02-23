from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import StrOutputParser

load_dotenv()

model = ChatOpenAI()


# 1st Prompt - Detailed Report
prompt1 = PromptTemplate(
    template='Write a detailed report on {topic}',
    input_variables=['topic']
)

prompt2 = PromptTemplate(
    template='Write small on \n{text} \n I want in markdown and to the point',
    input_variables=['text']
)

parser = StrOutputParser()

chain = prompt1 | model |  parser | model | parser  
result = chain.invoke({'topic':'Chain In Langchain'})

print(result)
chain.get_graph().print_ascii()