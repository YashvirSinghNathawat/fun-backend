from langchain_openai import ChatOpenAI
from langchain_core.messages import SystemMessage, HumanMessage, AIMessage
from dotenv import load_dotenv

load_dotenv()

model = ChatOpenAI(model="gpt-3.5-turbo")

messages = [
    SystemMessage(content='You are a helpful assistant'),
    HumanMessage(content='Tell me about langchain')
]

result = model.invoke(messages)

messages.append(AIMessage(content=result.content))

print(messages)