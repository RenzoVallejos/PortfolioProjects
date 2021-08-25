/*
Here we are taking a look at Covid 19 Data Exploration. Specifically we are looking at covid deaths vs covid vaccinations and its current impract to society.
Skills used: Joins, CTE's, Temp Tables, Windows Functions, Creating Views, Storing data, Converting Data Types
*/

Select *
From PortfolioProject.CovidDeaths
Where continent is not null 


-- Selecting Data that we are going to be starting with

Select Location, date, total_cases, new_cases, total_deaths, population
From PortfolioProject.CovidDeaths
Where continent is not null 


-- Showing the likelihood of death if an individual contracts the virus in the United States.

Select Location, date, total_cases,total_deaths, (total_deaths/total_cases)*100 as DeathPercentage
From PortfolioProject.CovidDeaths
Where location like '%states%'
and continent is not null 


-- Showing what percentage of population infected with Covid

Select Location, date, Population, total_cases,  (total_cases/population)*100 as PercentPopulationInfected
From PortfolioProject.CovidDeaths
Where location like '%states%'


-- Taking a look at countries with Highest Infection Rate compared to Population

Select Location, Population, MAX(total_cases) as HighestInfectionCount,  Max((total_cases/population))*100 as PercentPopulationInfected
From PortfolioProject.CovidDeaths
--Where location like '%states%'
Group by Location, Population
order by PercentPopulationInfected desc


-- Countries with Highest Death Count per Population

Select Location, MAX(cast(Total_deaths as int)) as TotalDeathCount
From PortfolioProject.CovidDeaths
Where location like '%states%'
Where continent is not null 
Group by Location
order by TotalDeathCount desc



-- BREAKING THINGS DOWN BY CONTINENT

-- Showing contintents with the highest death count per population

Select continent, MAX(cast(Total_deaths as int)) as TotalDeathCount
From PortfolioProject.CovidDeaths
--Where location like '%states%'
Where continent is not null 
Group by continent
order by TotalDeathCount desc



-- Analyzing global numbers across the country.

Select SUM(new_cases) as total_cases, SUM(cast(new_deaths as int)) as total_deaths, SUM(cast(new_deaths as int))/SUM(New_Cases)*100 as DeathPercentage
From PortfolioProject.CovidDeaths
--Where location like '%states%'
where continent is not null 
--Group By date
order by 1,2



-- Total Population vs Vaccinations
-- Showing Percentage of Population that has recieved at least one Covid Vaccine

Select death.continent, death.location, death.date, death.population, vaccine.new_vaccinations
, SUM(CONVERT(int,vaccine.new_vaccinations)) OVER (Partition by death.Location Order by death.location, death.Date) as RollingPeopleVaccinated
--, (RollingPeopleVaccinated/population)*100
From PortfolioProject.CovidDeaths death
Join PortfolioProject.CovidVaccinations vaccine
	On death.location = vaccine.location
	and death.date = vaccine.date
where death.continent is not null 



-- Using CTE to perform Calculation on Partition By in previous query

With PopvsVac (Continent, Location, Date, Population, New_Vaccinations, RollingPeopleVaccinated)
as
(
Select death.continent, death.location, death.date, death.population, vaccine.new_vaccinations
, SUM(CONVERT(int,vaccine.new_vaccinations)) OVER (Partition by death.Location Order by death.location, death.Date) as RollingPeopleVaccinated
--, (RollingPeopleVaccinated/population)*100
From PortfolioProject.CovidDeaths death
Join PortfolioProject.CovidVaccinations vaccine
	On death.location = vaccine.location
	and death.date = vaccine.date
where death.continent is not null 
--order by 2,3
)
Select *, (RollingPeopleVaccinated/Population)*100
From PopvsVac



-- Using Temp Table to perform Calculation on Partition By in previous query

DROP Table if exists #PercentPopulationVaccinated
Create Table #PercentPopulationVaccinated
(
Continent nvarchar(255),
Location nvarchar(255),
Date datetime,
Population numeric,
New_vaccinations numeric,
RollingPeopleVaccinated numeric
)

Insert into #PercentPopulationVaccinated
Select death.continent, death.location, death.date, death.population, vaccine.new_vaccinations
, SUM(CONVERT(int,vaccine.new_vaccinations)) OVER (Partition by death.Location Order by death.location, death.Date) as RollingPeopleVaccinated
--, (RollingPeopleVaccinated/population)*100
From PortfolioProject.CovidDeaths dea
Join PortfolioProject.CovidVaccinations vac
	On death.location = vaccine.location
	and death.date = vaccine.date
--where death.continent is not null 


Select *, (RollingPeopleVaccinated/Population)*100
From #PercentPopulationVaccinated


-- Creating View to store data for later visualizations

Create View PercentPopulationVaccinated as
Select death.continent, death.location, death.date, death.population, vaccine.new_vaccinations
, SUM(CONVERT(int,vaccine.new_vaccinations)) OVER (Partition by death.Location Order by death.location, death.Date) as RollingPeopleVaccinated
--, (RollingPeopleVaccinated/population)*100
From PortfolioProject.CovidDeaths death
Join PortfolioProject.CovidVaccinations vaccine
	On death.location = vaccine.location
	and death.date = vaccine.date
where death.continent is not null 