package com.enrutaglp.algorithmstar;

import java.util.ArrayList;

public class Nodo{
	int x;
	int y;
	ArrayList<Nodo> neighborList;
	Mapa map;
	Nodo norte;
	Nodo este;
	Nodo sur;
	Nodo oeste;
	float distanciaDesdePrincipio;
	float distanciaHeuristicaALaMeta;
	Nodo previoNodo;
	boolean esObstaculo;
	boolean esVisitado;
	boolean esInicio;
	boolean esMeta;
	
	
	public Nodo(int x, int y) {
		neighborList=new ArrayList<Nodo>();
		this.x=x;
		this.y=y;
		distanciaDesdePrincipio=Integer.MAX_VALUE;
		esVisitado=false;
		esObstaculo=false;
		esInicio=false;
		esMeta=false;
	}
	public Nodo(int x, int y, boolean visita, int distDesdePrin, boolean esObstacul, boolean esIni, boolean esMet) {
		neighborList=new ArrayList<Nodo>();
		this.x=x;
		this.y=y;
		distanciaDesdePrincipio=distDesdePrin;
		esVisitado=visita;
		esObstaculo=esObstacul;
		esInicio=esIni;
		esMeta=esMet;
				
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y=y;
	}
	public Nodo getNorte() {
		return norte;
	}
	public void setNorte(Nodo nort) {
		if(neighborList.contains(norte)) {
			neighborList.remove(norte);
		}
		neighborList.add(nort);
		norte=nort;
	}
	public Nodo getEste() {
		return este;
	}
	public void setEste(Nodo est) {
		if(neighborList.contains(este)) {
			neighborList.remove(este);
		}
		neighborList.add(est);
		este=est;
	}
	public Nodo getSur() {
		return sur;
	}
	public void setSur(Nodo su) {
		if(neighborList.contains(sur)) {
			neighborList.remove(sur);
		}
		neighborList.add(su);
		sur=su;
	}
	public Nodo getOeste() {
		return oeste;
	}
	public void setOeste(Nodo oest) {
		if(neighborList.contains(oeste)) {
			neighborList.remove(oeste);
		}
		neighborList.add(oest);
		oeste=oest;
	}
	public ArrayList<Nodo> getNeighborList(){
		return neighborList;
	}
	public boolean getEsVisitado() {
		return esVisitado;
	}
	public void setEsVisitado(boolean esVisit) {
		esVisitado=esVisit;
	}
	public float getDistanciaDesdePrincipio() {
		return distanciaDesdePrincipio;
	}
	public void setDistanciaDesdePrincipio(float dist) {
		distanciaDesdePrincipio=dist;
	}
	public float getDistanciaHeuristicaALaMeta() {
		return distanciaHeuristicaALaMeta;
	}
	public void setDistanciaHeuristicaALaMeta(float dist) {
		distanciaHeuristicaALaMeta=dist;
	}
	public Nodo getPrevioNodo() {
		return previoNodo;
	}
	public void setPrevioNodo(Nodo prevNod) {
		previoNodo=prevNod;
	}
	public boolean getEsObstaculo() {
		return esObstaculo;
	}
	public void setEsObstaculo(boolean esObstaculo) {
		this.esObstaculo=esObstaculo;
	}
	public boolean getEsInicio() {
		return esInicio;
	}
	public void setEsInicio(boolean esInicio) {
		this.esInicio=esInicio;
	}
	public boolean getEsMeta() {
		return esMeta;
	}
	public void setEsMeta(boolean esmeta) {
		this.esMeta=esmeta;
	}
	public boolean iguales(Nodo nod) {
		return (nod.x==x)&&(nod.y==y);
	}
	public int compararMasCercanoAMeta(Nodo otroNodo) {
		float distanciaTotal=distanciaDesdePrincipio+distanciaHeuristicaALaMeta;
		float otroDistanciaTotal=otroNodo.getDistanciaDesdePrincipio()+otroNodo.getDistanciaHeuristicaALaMeta();
		if(distanciaTotal>otroDistanciaTotal) {
			return -1;
		}else if(distanciaTotal==otroDistanciaTotal) {
			return 0;
		}else {
			return 1;
		}
	}
}