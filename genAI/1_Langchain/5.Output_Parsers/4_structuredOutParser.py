from langchain_huggingface import ChatHuggingFace, HuggingFaceEndpoint
from langchain_core.prompts import PromptTemplate
from langchain.output_parsers import StructuredOutputParser, ResponseSchema
from dotenv import load_dotenv

load_dotenv()

llm = HuggingFaceEndpoint(
    repo_id="google/medgemma-1.5-4b-it",
    task="text-generation"
)

model = ChatHuggingFace(llm=llm)

schema = [
    ResponseSchema(name='fact_1', description='Fact 1 of the topic'),
    ResponseSchema(name='fact_2', description='Fact 2 of the topic'),
    ResponseSchema(name='fact_3', description='Fact 3 of the topic')
]

parser = StructuredOutputParser.from_response_schema(schema)

template = PromptTemplate(
    template='Give 3 facts about the topic \n {format_instruction}',
    input_variables=[],
    partial_variables={'format_instruction': parser.get_format_instructions()}
)

chain = template | model | parser
final_result = chain.invoke({'topic' : 'black hole'})
print(final_result)