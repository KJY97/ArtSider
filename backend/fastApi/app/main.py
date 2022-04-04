from fastapi import FastAPI, responses

from app.routers import recommendations_data_analysys

app = FastAPI()

@app.get("/")
def main():
    return responses.RedirectResponse(url="/docs/")

app.include_router(recommendations_data_analysys.router)