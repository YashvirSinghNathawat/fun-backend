from langchain_openai import ChatOpenAI
from dotenv import load_dotenv
from langchain_core.prompts import PromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_core.runnables import RunnableParallel

load_dotenv()

model = ChatOpenAI()


# 1st Prompt - Detailed Report
prompt1 = PromptTemplate(
    template='Write short notes on \n{text}',
    input_variables=['text']
)


prompt2 = PromptTemplate(
    template='Write 5 short questions on \n {text} ',
    input_variables=['text']
)

prompt3 = PromptTemplate(
    template="""
    Combine the following into a single formatted document.

    NOTES:
    {notes}

    QUIZ:
    {quiz}

    Keep both sections clearly visible.
    Do not remove or summarize anything.
    """,
    input_variables=['notes','quiz']
)

parser = StrOutputParser()

parallel_chain = RunnableParallel({
    'notes': prompt1 | model | parser,
    'quiz' : prompt2 | model | parser,
})

merge_chain = prompt3 | model | parser

chain = parallel_chain | merge_chain

text = """
From Garage to Global Giant: The Incredible History of Google
Imagine a world where finding a specific webpage took hours, and search engines rarely gave you what you wanted. In the mid-1990s, that was the internet. Then, two Stanford Ph.D. students, Larry Page and Sergey Brin, decided to map the web’s links.
Their project, initially called "BackRub," revolutionized how information is organized, transforming from a dorm-room project into a global technology giant that shapes how we live, work, and play today.
1. The "BackRub" Beginnings (1995–1997)
In 1995, Larry Page and Sergey Brin met at Stanford University. While they initially disagreed on nearly everything, they found common ground in a shared goal: to analyze the link structure of the World Wide Web.
The Idea: Traditional search engines ranked results based on how often a search term appeared on a page. Page and Brin argued that a better system would rank pages based on how many other high-quality websites linked to them—a metric they called PageRank.
BackRub: They built a search engine nicknamed "BackRub" because it analyzed backlinks to determine a site's importance.
Renaming to Google: The name "Google" was chosen as a play on "googol," a mathematical term for a 1 followed by 100 zeros, representing their mission to organize the immense volume of information on the web.
2. The Garage Days & Official Founding (1998)
The project grew quickly, outgrowing Stanford dorm rooms. In August 1998, with an investment of $100,000 from Sun co-founder Andy Bechtolsheim, "Google Inc." was established. The company's first office was in a Menlo Park, California garage. The first "Google Doodle" was created in 1998, a stick figure indicating the founders' absence at the Burning Man Festival. By the end of 1998, Google had indexed approximately 60 million pages.
3. The Move to the "Googleplex" & Public Offering (1999–2004)
By 1999, Google had moved to Palo Alto, California, and eventually relocated to their current Mountain View headquarters, known as the "Googleplex," in 2003. On August 19, 2004, Google's initial public offering (IPO) raised $1.67 billion, valuing the company at over $23 billion. The launch of AdWords (now Google Ads) in 2000, allowing businesses to place text ads based on search keywords, became a crucial financial component for the company.
"""
result = chain.invoke({
    'text':text
})

print(result)
chain.get_graph().print_ascii()