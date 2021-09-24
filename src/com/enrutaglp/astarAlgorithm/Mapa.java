package com.enrutaglp.astarAlgorithm;

import java.util.ArrayList;

public class Mapa{
	int anchoMapa;
	int alturaMapa;
	ArrayList<ArrayList<Nodo>> mapa;
	int posIniX=0;
	int posIniY=0;
	int posFinX=0;
	int posFinY=0;
	int [][] mapaObstaculos;
	
	public Mapa(int ancho, int alto/*, int[][]obstaculoMapa*/) {
		this.anchoMapa=ancho;
		this.alturaMapa=alto;
		this.mapaObstaculos=new int[ancho+1][alto+1];
		this.mapaObstaculos[20][0]=1;
		this.mapaObstaculos[20][1]=1;
		this.mapaObstaculos[20][11]=1;
		this.mapaObstaculos[20][12]=1;
		this.mapaObstaculos[20][13]=1;
		this.mapaObstaculos[20][14]=1;
		this.mapaObstaculos[20][15]=1;
		this.mapaObstaculos[20][16]=1;
		this.mapaObstaculos[20][17]=1;
		this.mapaObstaculos[20][18]=1;
		this.mapaObstaculos[20][19]=1;
		this.mapaObstaculos[20][20]=1;
		this.mapaObstaculos[20][21]=1;
		this.mapaObstaculos[20][22]=1;
		this.mapaObstaculos[20][23]=1;
		this.mapaObstaculos[20][24]=1;
		this.mapaObstaculos[20][25]=1;
		this.mapaObstaculos[20][26]=1;
		this.mapaObstaculos[20][27]=1;
		this.mapaObstaculos[20][28]=1;
		this.mapaObstaculos[20][29]=1;
		this.mapaObstaculos[20][30]=1;
		this.mapaObstaculos[20][31]=1;
		this.mapaObstaculos[20][32]=1;
		this.mapaObstaculos[20][33]=1;
		crearMapa();
		bordeMapa();
	}
	private void crearMapa() {
		Nodo node;
		mapa=new ArrayList<ArrayList<Nodo>>();
		for(int x=0; x<anchoMapa;x++) {
			mapa.add(new ArrayList<Nodo>());
			for(int y=0;y<alturaMapa;y++) {
				node=new Nodo(x,y);
				if(mapaObstaculos[x][y]==1) {
					node.setEsObstaculo(true);
				}
				mapa.get(x).add(node);
			}
		}
				
	}
	private void bordeMapa() {
		for(int x=0;x<anchoMapa;x++) {
			for(int y=0;y<alturaMapa;y++) {
				Nodo node=mapa.get(x).get(y);
				if(!(y==0)) {
					node.setNorte(mapa.get(x).get(y-1));
				}
				if(!(x==anchoMapa-1)) {
					node.setEste(mapa.get(x+1).get(y));
				}
				if(!(y==alturaMapa-1)) {
					node.setSur(mapa.get(x).get(y+1));
				}
				if(!(x==0)) {
					node.setOeste(mapa.get(x-1).get(y));
				}
			}
		}
	}
	public ArrayList<ArrayList<Nodo>> getNodos(){
		return mapa;
	}
	public void setObstaculo(int x, int y, boolean esObstacul) {
		mapa.get(x).get(y).setEsObstaculo(esObstacul);
	}
	public Nodo getNodo(int x, int y) {
		return mapa.get(x).get(y);
	}
	public void setPosicionInicial(int x, int y) {
		mapa.get(posIniX).get(posIniY).setEsInicio(false);
		mapa.get(x).get(y).setEsInicio(true);
		posIniX=x;
		posIniY=y;
	}
	public void setPosicionMeta(int x, int y) {
		mapa.get(posFinX).get(posFinY).setEsMeta(false);
		mapa.get(x).get(y).setEsMeta(true);
		posFinX=x;
		posFinY=y;
	}
	public int getPosIniX() {
		return posIniX;
	}
	public int getPosIniY() {
		return posIniY;
	}
	public Nodo getNodoInicial() {
		return mapa.get(posIniX).get(posIniY);
	}
	public int getPosFinX() {
		return posFinX;
	}
	public int getPosFinY() {
		return posFinY;
	}
	public Nodo getNodoFinal() {
		return mapa.get(posFinX).get(posFinY);
	}
	public ArrayList<ArrayList<Nodo>> getMapa(){
		return mapa;
	}
	public float distanciaEntreNodos(Nodo nod1, Nodo nod2) {
		if(nod1.getX()==nod2.getX() || nod1.getY()==nod2.getY()) {
			return 1*(alturaMapa+anchoMapa);//en duda
		}else {
			return (float)1.7*(alturaMapa+anchoMapa);//en duda
		}
	}
	public int getAnchoMapa() {
		return anchoMapa;
	}
	public int getAlturaMapa() {
		return alturaMapa;
	}
	public int[][] getMapaObstaculos(){
		return mapaObstaculos;
	}
	public void clear(int [][]obstaculoMapa) {
		posIniX=0;
		posIniY=0;
		posFinX=0;
		posFinY=0;
		crearMapa();
		bordeMapa();
		this.mapaObstaculos=obstaculoMapa;
	}
}
