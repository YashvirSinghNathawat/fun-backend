from langchain_openai import OpenAIEmbeddings
from dotenv import load_dotenv

load_dotenv()

docs = [
    "Delhi is the capital of India.",
    "The Sun rises in the east.",
    "Water freezes at zero degrees Celsius.",
    "The Earth revolves around the Sun.",
    "A week has seven days."
]


embedding = OpenAIEmbeddings(model='text-embedding-3-large', dimensions = 32)

result = embedding.embed_documents(docs)

print(str(result))