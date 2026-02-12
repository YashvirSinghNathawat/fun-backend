from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import StrOutputParser



load_dotenv()

model = ChatOpenAI()


# 1st Prompt - Detailed Report
prompt = PromptTemplate(
    template='Write a 5 facts about {topic}',
    input_variables=['topic']
)

parser = StrOutputParser()



chain = prompt | model | parser   
result = chain.invoke({'topic':'cricket'})

chain.get_graph().print_ascii()
print(result)